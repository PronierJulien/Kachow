package kachow.service;

import kachow.dao.MonstreDao;
import kachow.model.Monstre;
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

    public void createMonstre(Monstre monstre) {
        monstreDao.save(monstre);
    }

    public void save(Monstre monstre) {
        Monstre savedMonstre = monstreDao.save(monstre);
    }

    public Monstre getMonstre(UUID id) {
        return monstreDao.findById(id).orElse(null);
    }

    public void lvlUp(Monstre monstre) {
        monstre.setLvl(monstre.getLvl() + 1);
        monstre.setXp_for_lvlup((int) (monstre.getXp_for_lvlup() * 1.1));
        monstre.setHp((int) (monstre.getHp() * 1.1));
        monstre.setDef((int) (monstre.getDef() * 1.1));
        monstre.setAtk((int) (monstre.getAtk() * 1.1));
        monstre.setVit((int) (monstre.getVit() * 1.1));
        monstre.setAvailable_lvl(monstre.getAvailable_lvl() + 1);
        monstre.setXp(0);
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
