package zaliczenie.Hosting_photos.service;

import org.springframework.stereotype.Service;
import zaliczenie.Hosting_photos.model.Komentarz;
import zaliczenie.Hosting_photos.model.Opis;
import zaliczenie.Hosting_photos.repo.KomentarzRepo;
import zaliczenie.Hosting_photos.repo.OpisRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpisUploader {

    private OpisRepo opisRepo;

    public OpisUploader(zaliczenie.Hosting_photos.repo.OpisRepo opisRepo) {
        this.opisRepo = opisRepo;
    }

    public void save(Opis opis) {
        opisRepo.save(opis);
    }


    public List<Opis> lista_ops(){

        return  opisRepo.findAll();
    }


}
