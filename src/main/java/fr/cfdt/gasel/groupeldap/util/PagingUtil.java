package fr.cfdt.gasel.groupeldap.util;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author SZaoui
 */
@Component
public class PagingUtil {

    public List getPage(List<?> resultList, Integer page, Integer rows){
        //rows = nombre de igne par page
        if(resultList != null && !resultList.isEmpty()) {
            //la gestion de la pagination
            if(page != null) {
                int to = rows;
                int from = 0;
                if(page > 0) {
                    to = (rows * page);
                    from = to - rows;
                }
                if (to > resultList.size()){
                    to = resultList.size();
                }
                resultList = resultList.subList(from, to);
            }
        }
        return resultList;
    }

}
