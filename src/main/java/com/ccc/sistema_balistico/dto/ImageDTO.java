package com.ccc.sistema_balistico.dto;

import lombok.Builder;
import org.springframework.core.io.Resource;

@Builder
public record ImageDTO(Resource img,String contentType) {

}
