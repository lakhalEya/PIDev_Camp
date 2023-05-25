package backend.src.main.java.tn.camps.tuncamps.persistence.entity.forum;

import backend.src.main.java.tn.camps.tuncamps.persistence.entity.enumeration.CommunityCategory;

public class CommunitySpace
{
    private int idForum;
    String titre;
    String description;
//    @Enumerated(EnumType.STRING)
    CommunityCategory category;
}
