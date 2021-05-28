package fr.hugodegrossi.roleplaymanager.request.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoStringAndListRequest {
    private String postedStringOne;
    private String postedStringTwo;
    private List<Integer> postedList;
}
