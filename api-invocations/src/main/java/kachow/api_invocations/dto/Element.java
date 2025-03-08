package kachow.api_invocations.dto;

import kachow.api_invocations.model.Type;

public enum Element {
    fire,
    wind,
    water;

    public static Type toType(Element element){
        if (element == Element.fire){
            return Type.FEU;
        }
        if (element == Element.wind){
            return Type.VENT;
        }
        if (element == Element.water){
            return Type.EAU;
        }
        return null;
    }
}
