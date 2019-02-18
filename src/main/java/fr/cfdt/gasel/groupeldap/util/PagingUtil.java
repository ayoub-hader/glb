package fr.cfdt.gasel.groupeldap.util;

import fr.cfdt.gasel.groupeldap.model.Personne;
import fr.cfdt.gasel.groupeldap.model.Structure;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public List sortColumn(List<?> resultList, String orderDir, String orderCol , String object){
        List<?> tmp = null;
        if(resultList != null && !resultList.isEmpty() && orderDir != null && orderCol != null) {
            if(object.equalsIgnoreCase("structure")){
                List<Structure> structures = new ArrayList<>((Collection<? extends Structure>) resultList);
                tmp = sortListStructure(structures , orderCol);
            } else {
                List<Personne> members = new ArrayList<>((Collection<? extends Personne>) resultList);
                tmp = sortListMember(members , orderCol);
            }
            if (orderDir != null && orderDir.equalsIgnoreCase("desc")) {
                Collections.reverse(tmp);
            }
        }
        return tmp;
    }

    private List sortListStructure(List<Structure> resultList, String orderCol){
        if(orderCol.equalsIgnoreCase("matricule")){
            resultList.sort(Comparator.comparing(Structure::getMatricule));
        } else if(orderCol.equalsIgnoreCase("nom")){
            resultList.sort(Comparator.comparing(Structure::getNom));
        }
        return resultList;
    }

    private List sortListMember(List<Personne> resultList, String orderCol){
        if(orderCol.equalsIgnoreCase("matricule")){
            resultList.sort(Comparator.comparing(m -> m.getSyndicat().getMatricule()));
        } else if(orderCol.equalsIgnoreCase("nom")){
            resultList.sort(Comparator.comparing(Personne::getNom));
        } else if(orderCol.equalsIgnoreCase("npa")){
            resultList.sort(Comparator.comparing(Personne::getNpa));
        } else if(orderCol.equalsIgnoreCase("syndicat")){
            resultList.sort(Comparator.comparing(m -> m.getSyndicat().getAcronyme()));
        }
        return resultList;
    }

}
