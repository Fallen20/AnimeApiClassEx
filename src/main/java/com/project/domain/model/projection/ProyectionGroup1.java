package com.project.domain.model.projection;

import java.util.Set;
import java.util.UUID;

public interface ProyectionGroup1 {
    UUID getGroupid();
    String getNombre_grupo();
    String getDescripcion();

    Set<ProyectionGroup2> getMiembros();
}
