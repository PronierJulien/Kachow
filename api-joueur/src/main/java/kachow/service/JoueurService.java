package kachow.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kachow.dao.JoueurDao;
import kachow.model.Joueur;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class JoueurService {
    private final JoueurDao joueurDao;


    public JoueurService(JoueurDao joueurDao) {
        this.joueurDao = joueurDao;
    }

    public void saveJoueur(Joueur joueur) {
        joueurDao.save(joueur);
    }

    public Joueur getJoueur(String joueurId) {
        return joueurDao.findById(joueurId).orElse(null);
    }

    public String createJoueur(String joueurId) {
        Joueur joueur = new Joueur(joueurId);
        joueurDao.save(joueur);
        return joueur.getId();
    }

    public void lvlUp(Joueur joueur) {
        joueur.setLvl(joueur.getLvl() + 1);
        joueur.setXp_for_lvlup((int) (joueur.getXp_for_lvlup() * 1.1));
        joueur.setXp(0);
        joueur.setMax_monstres(joueur.getMax_monstres() + 1);
        joueurDao.save(joueur);
    }

    public void addXp(String joueurId, int xp) {
        Joueur joueur = getJoueur(joueurId);
        joueur.setXp(joueur.getXp() + xp);
        if (joueur.getXp() >= joueur.getXp_for_lvlup()) {
            lvlUp(joueur);
        }
        joueurDao.save(joueur);
    }

    public boolean addMonstre(String joueurId, String monstreId) {
        Joueur joueur = getJoueur(joueurId);
        if (joueur.getNb_monstres() >= joueur.getMax_monstres()) {
            return false;
        }
        joueur.getMonstres().add(monstreId);
        joueur.setNb_monstres(joueur.getNb_monstres() + 1);
        joueurDao.save(joueur);
        return true;
    }

    public void removeMonstre(String joueurId, String monstreId) {
        Joueur joueur = getJoueur(joueurId);
        joueur.getMonstres().remove(monstreId);
        joueur.setNb_monstres(joueur.getNb_monstres() - 1);
        joueurDao.save(joueur);
    }

    public HashMap<String, Object> getJoueurInfo(String joueurId) {
        Joueur joueur = getJoueur(joueurId);
        HashMap<String, Object> joueurInfo = new HashMap<>();
        joueurInfo.put("id", joueur.getId());
        joueurInfo.put("lvl", joueur.getLvl());
        joueurInfo.put("xp", joueur.getXp());
        joueurInfo.put("max_monstres", joueur.getMax_monstres());
        joueurInfo.put("nb_monstres", joueur.getNb_monstres());
        joueurInfo.put("xp_for_lvlup", joueur.getXp_for_lvlup());
        joueurInfo.put("monstres", joueur.getMonstres());
        return joueurInfo;
    }

    public String verifyToken(String token, String AuthUrl) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(AuthUrl, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
    
}
