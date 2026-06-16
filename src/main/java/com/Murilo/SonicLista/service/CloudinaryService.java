package com.Murilo.SonicLista.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    // Construtor que pega a URL do application.yaml e liga o motor do Cloudinary
    public CloudinaryService(@Value("${cloudinary.url}") String cloudinaryUrl) {
        this.cloudinary = new Cloudinary(cloudinaryUrl);
    }

    public String fazerUpload(MultipartFile file) {
        try {
            // Faz o upload do arquivo em bytes diretamente para a nuvem
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            
            // Retorna a URL segura (https) da imagem salva
            return uploadResult.get("secure_url").toString();
            
        } catch (IOException e) {
            System.err.println("Erro ao fazer upload para o Cloudinary: " + e.getMessage());
            return null;
        }
    }
}