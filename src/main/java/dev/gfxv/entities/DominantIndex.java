package dev.gfxv.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class DominantIndex {

    private int index;
    private boolean strict;
}
