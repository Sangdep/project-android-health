package com.SelfCare.SelftCare.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Lombok tự động tạo constructor cho biến final (cloudinary)
public class FileUploadsService {

    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        // 1. Kiểm tra file rỗng
        if (file == null || file.isEmpty()) {
            return null; // Trả về null nếu không có file, để Service gọi nó tự xử lý
        }

        // 2. Upload lên Cloudinary
        // "public_id": Đặt tên file là UUID để không bị trùng lặp trên Cloudinary
        // "resource_type": "auto" để tự nhận diện là ảnh/video
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "public_id", UUID.randomUUID().toString(),
                "resource_type", "auto",
                "folder", "user_avatars" // (Tuỳ chọn) Gom ảnh vào thư mục riêng cho gọn
        ));

        // 3. Lấy đường dẫn ảnh HTTPS (secure_url) trả về
        return uploadResult.get("secure_url").toString();
    }
}