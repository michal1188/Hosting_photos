package zaliczenie.Hosting_photos.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zaliczenie.Hosting_photos.model.Galleria;
import zaliczenie.Hosting_photos.model.Image;
import zaliczenie.Hosting_photos.repo.GalleryRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class GalleryUploader {

    private GalleryRepo galleryRepo;


    @Autowired
    public GalleryUploader(GalleryRepo galleryRepo) {
        this.galleryRepo = galleryRepo;
    }


    public void save(Galleria galleria){
        galleryRepo.save(galleria);
    }

    public  List<String> lista_gallerii(){
List<String> list =new ArrayList<>();

    for(Galleria galleria :galleryRepo.findAll()){
        list.add(galleria.getNazwa());
    }
return list;
    }

    public  List<Galleria> lista_gallerii2(){
        List<Galleria> list =new ArrayList<>();

        for(Galleria galleria :galleryRepo.findAll()){
            list.add(galleria);
        }
        return list;
    }

    public void delete_item(int id){

        galleryRepo.delete(lista_gallerii2().get(id));

    }





}
