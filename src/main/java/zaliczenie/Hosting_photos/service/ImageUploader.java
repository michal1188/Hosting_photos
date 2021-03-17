package zaliczenie.Hosting_photos.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.helger.commons.state.IMandatoryIndicator;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.ls.LSOutput;
import zaliczenie.Hosting_photos.model.Galleria;
import zaliczenie.Hosting_photos.model.Image;
import zaliczenie.Hosting_photos.model.Opis;
import zaliczenie.Hosting_photos.repo.GalleryRepo;
import zaliczenie.Hosting_photos.repo.ImageRepo;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ImageUploader {

    private Cloudinary cloudinary;
    private ImageRepo imageRepo;
    private GalleryRepo galleryRepo;
private  List<Image>imageList;


    @Autowired
    public ImageUploader(ImageRepo imageRepo, GalleryRepo galleryRepo) {
        this.imageRepo = imageRepo;
        this.galleryRepo = galleryRepo;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dzmsneqsv",
                "api_key", "398393866338821",
                "api_secret", "ZWNGbcGwuxw5iIp4V97MmVfy05E"));

    }


    public String uploadFileAndSaveToDb(String path) {

        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploadResult.get("url").toString();
    }

    public void save(Image image) {
        imageRepo.save(image);


    }

    public void save(Galleria galleria) {
        galleryRepo.save(galleria);

    }

    public java.util.List<Image> lista_zdjec() {
        List<Image> list = new ArrayList<>();

        for (Image image : imageRepo.findAll()) {
            list.add(image);
        }
        return list;
    }

    public void delete_item(int id){

       imageRepo.delete(lista_zdjec().get(id));

    }

    public void update(Long id, Galleria galeria, String opis, String autor, String miejsce_zdjecia) {



                  Image image = imageRepo.findById(id).get();
                  image.setGalleria(galeria);
                  Opis opis1=new Opis(opis, autor, miejsce_zdjecia);
                  opis1.setImage(image);
                  List<Opis> ops = new ArrayList<>();
                  ops.add(opis1);
                  image.setOpis(ops);

                    imageRepo.save(image);
    }
}
