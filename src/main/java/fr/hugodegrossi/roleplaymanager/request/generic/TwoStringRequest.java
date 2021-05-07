package fr.hugodegrossi.roleplaymanager.request.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoStringRequest {
    private String postedStringOne;
    private String postedStringTwo;
}
