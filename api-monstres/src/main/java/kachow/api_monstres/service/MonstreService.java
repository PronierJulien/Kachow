package kachow.api_monstres.service;

import kachow.api_monstres.dao.MonstreDao;
import kachow.api_monstres.model.Monstre;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class MonstreService {
    private final MonstreDao monstreDao;

    public MonstreService(MonstreDao montreDao) {
        this.monstreDao = montreDao;
    }

    public Monstre createMonstre(Monstre monstre) {
        monstreDao.save(monstre);
        return monstre;
    }

    public void save(Monstre monstre) {
        Monstre savedMonstre = monstreDao.save(monstre);
    }

    public Monstre getMonstre(UUID id) {
        return monstreDao.findById(id).orElse(null);
    }

    public void lvlUp(Monstre monstre) {
        int new_xp = monstre.getXp() - monstre.getXp_for_lvlup();
        monstre.setLvl(monstre.getLvl() + 1);
        monstre.setXp_for_lvlup((int) (monstre.getXp_for_lvlup() * 1.1));
        if (new_xp > 0) {
            monstre.setXp(new_xp);
        } else {
            monstre.setXp(0);
        }
        if (monstre.getXp() >= monstre.getXp_for_lvlup()) {
            lvlUp(monstre);
        }
        monstreDao.save(monstre);
    }

    public void addXp(UUID monstreId, int xp) {
        Monstre monstre = getMonstre(monstreId);
        monstre.setXp(monstre.getXp() + xp);
        if (monstre.getXp() >= monstre.getXp_for_lvlup()) {
            lvlUp(monstre);
        }
        monstreDao.save(monstre);
    }

    public HashMap<String, Object> getMonstreInfo(UUID monstreId) {
        Monstre monstre = getMonstre(monstreId);
        HashMap<String, Object> monstreInfo = new HashMap<>();
        monstreInfo.put("id", monstre.getId());
        monstreInfo.put("lvl", monstre.getLvl());
        monstreInfo.put("xp", monstre.getXp());
        monstreInfo.put("xp_for_lvlup", monstre.getXp_for_lvlup());
        monstreInfo.put("hp", monstre.getHp());
        monstreInfo.put("def", monstre.getDef());
        monstreInfo.put("atk", monstre.getAtk());
        monstreInfo.put("vit", monstre.getVit());
        return monstreInfo;
    }

    public void lvlupCompetence(UUID monstreId, int competence) {
        Monstre monstre = getMonstre(monstreId);
        if(monstre.getAvailable_lvl() >= 0)
            monstre.getCompetences().get(competence).levelUp();
        monstreDao.save(monstre);
    }

    public boolean isAuthorized(UUID monstreId, String joueurId) {
        Monstre monstre = getMonstre(monstreId);
        return monstre.getIdJoueur().equals(joueurId);
    }


}
