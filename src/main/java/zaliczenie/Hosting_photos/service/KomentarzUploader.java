package zaliczenie.Hosting_photos.service;

import org.springframework.stereotype.Service;
import zaliczenie.Hosting_photos.model.Image;
import zaliczenie.Hosting_photos.model.Komentarz;
import zaliczenie.Hosting_photos.repo.KomentarzRepo;

import java.util.List;

@Service
public class KomentarzUploader {

    private KomentarzRepo komentarzRepo;

    public KomentarzUploader(KomentarzRepo komentarzRepo) {
        this.komentarzRepo = komentarzRepo;
    }

    public void save(Komentarz komentarz) {
        komentarzRepo.save(komentarz);
    }


    public List<Komentarz> lista_kom(){

        return  komentarzRepo.findAll();
    }
}