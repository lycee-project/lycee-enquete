package net.lycee.web.enquete.api.controller.space;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SpacePostRequest {
    @NotEmpty
    private String name;

    @NotNull
    private Long closeTime;
}
