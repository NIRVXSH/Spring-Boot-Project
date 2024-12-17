package com.example.demo.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class UtilService {

    
    public  <E, D> D mapEntityToDto(E entity, Class<D> dtoClass, Map<String, List<String>> transformationRules) {
        ObjectMapper objectMapper = new ObjectMapper(); // ใช้สำหรับแปลง String JSON เป็น HashMap
        D dto;

        try {
            // สร้างอินสแตนซ์ของ DTO จากคลาสที่รับมา
            dto = dtoClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create DTO instance", e);
        }

        // ไล่เช็คฟิลด์ทั้งหมดใน Entity
        for (Field entityField : entity.getClass().getDeclaredFields()) {
            String fieldName = entityField.getName(); // ชื่อฟิลด์ใน Entity
            try {
                // ทำให้สามารถเข้าถึงฟิลด์ได้แม้จะเป็น private
                entityField.setAccessible(true);
                Object value = entityField.get(entity); // ดึงค่าจากฟิลด์ใน Entity

                // ตรวจสอบว่าฟิลด์อยู่ใน transformationRules หรือไม่
                
                boolean isTransformed = false;
                if(transformationRules!=null){
                    for (Map.Entry<String, List<String>> ruleEntry : transformationRules.entrySet()) {
                        String transformationType = ruleEntry.getKey(); // เช่น "stringJsonToHashMap"
                        List<String> fieldsToTransform = ruleEntry.getValue();
    
                        if (fieldsToTransform.contains(fieldName)) {
                            isTransformed = true;
                            // ประเภทการแปลง: stringJsonToHashMap
                            if ("stringJsonToHashMap".equals(transformationType)) {
                                if (value instanceof String) {
                                    // แปลง String เป็น HashMap
                                    HashMap<String, String> map = objectMapper.readValue((String) value,
                                            new TypeReference<>() {
                                            });
                                    setField(dto, fieldName, map); // เซ็ตค่าที่แปลงแล้วลงใน DTO
                                }
                            } else if ("stringToInt".equals(transformationType)) {
                                if (value instanceof String) {
                                    // แปลง String เป็น Integer
                                    Integer intValue = Integer.parseInt((String) value);
                                    setField(dto, fieldName, intValue); // เซ็ตค่าที่แปลงแล้วลงใน DTO
                                }
                            }
                            break;
                        }
                    }
                }
                if (!isTransformed) {
                    // กรณีไม่มีการแปลง เซ็ตค่าจาก Entity ลง DTO ตรง ๆ
                    setField(dto, fieldName, value);
                }
            } catch (Exception e) {
                // จัดการข้อผิดพลาด เช่น การแปลงล้มเหลว
                e.printStackTrace();
            }
        }
        return dto; // คืนค่า DTO หลังการแปลง
    }

    private static <D> void setField(D dto, String fieldName, Object value) {
        try {
            if (dto.getClass().getDeclaredField(fieldName) != null) {
                Field dtoField = dto.getClass().getDeclaredField(fieldName); // หา Field ใน DTO ตามชื่อ
                dtoField.setAccessible(true); // ทำให้สามารถเข้าถึงได้แม้จะเป็น private
                dtoField.set(dto, value); // เซ็ตค่าลงใน Field
            }
        } catch (NoSuchFieldException e) {

        } catch (IllegalAccessException e) {
            // กรณีเกิดข้อผิดพลาดในการเข้าถึง Field
            e.printStackTrace();
        }
    }
}
