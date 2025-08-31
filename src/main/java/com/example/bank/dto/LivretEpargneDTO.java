package com.example.bank.dto;

import com.example.bank.model.enums.TypeLivretEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivretEpargneDTO extends CompteBancaireDTO {

    private TypeLivretEnum typeLivret;
}
