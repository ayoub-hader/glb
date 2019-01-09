package fr.cfdt.gasel.groupeldap.connector;

import fr.cfdt.gasel.groupeldap.enumeration.BranchEnum;
import fr.cfdt.gasel.groupeldap.enumeration.InstanceEnum;
import fr.cfdt.gasel.groupeldap.util.BusinessUtils;
import fr.cfdt.gasel.schema.v0.ebx.parametres.*;
import fr.cfdt.gasel.schema.v0.ebx.parametres.TypeStructureType.Parametres.TypeStructure;
import fr.cfdt.gasel.service.ebx.parametres.v0.ParametresEBXInterface;
import fr.cfdt.gasel.service.ebx.parametres.v0.StandardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Author SZaoui
 */
@Component
public class ParameterClient {

    @Autowired
    ParametresEBXInterface parametresEBXInterface;


    /**
     *
     * @param predicat
     * @return
     * @throws StandardException
     */
    public SelectTypeStructureResponseType findTypeStructureByPredicat(String predicat) throws StandardException {
        SelectTypeStructureRequestType typeStructureRequest = new SelectTypeStructureRequestType();
        typeStructureRequest.setBranch(BranchEnum.GASEL.getCode());
        typeStructureRequest.setInstance(InstanceEnum.PARAMETRE.getCode());
        typeStructureRequest.setPredicate(predicat);
        SelectTypeStructureResponseType typeStructureResponse = parametresEBXInterface.selectTypeStructureOperation(typeStructureRequest, BusinessUtils.securityTokenMapper());
        return typeStructureResponse;
    }

    /**
     *
     * @return
     * @throws StandardException
     */
    public List<TypeStructure> getAllTypeStructure() throws StandardException {
        SelectTypeStructureResponseType resp = findTypeStructureByPredicat("");
        List<TypeStructure> typeStructureList = new ArrayList<>();
        if (resp != null && resp.getData() != null && resp.getData().getParametres() != null && resp.getData().getParametres().getTypeStructure() != null) {
            typeStructureList.addAll(resp.getData().getParametres().getTypeStructure());
        }
        return typeStructureList;
    }


    /**
     *
     * @return
     * @throws StandardException
     */
    public List<RoleType.Parametres.Role> getListRoles() throws StandardException {
        return selectRoleByPredicat("", null);
    }

    /**
     *
     * @param predicat
     * @param paginationIn
     * @return
     * @throws StandardException
     */
    public List<RoleType.Parametres.Role> selectRoleByPredicat(final String predicat, final SelectRoleRequestType.Pagination paginationIn) throws StandardException {
        SelectRoleRequestType request = new SelectRoleRequestType();
        SelectRoleResponseType response = null;
        List<RoleType.Parametres.Role> result = null;

        request.setBranch(BranchEnum.GASEL.getCode());
        request.setInstance(InstanceEnum.PARAMETRE.getCode());
        request.setPredicate(predicat);

        if (parametresEBXInterface != null) {
            response = parametresEBXInterface.selectRoleOperation(request, BusinessUtils.securityTokenMapper());
        }

        if (response != null && response.getData().getParametres().getRole() != null && !response.getData().getParametres().getRole().isEmpty()) {
            result = response.getData().getParametres().getRole();
        }
        return result;
    }

    /**
     *
     * @return
     * @throws StandardException
     */
    public List<TypeResponsabiliteType.Parametres.TypeResponsabilite> getAllTypeResponsabilite() throws StandardException {
        return selectTypeResponsabiliteByPredicat("", null);
    }

    /**
     *
     * @param predicat
     * @param paginationIn
     * @return
     * @throws StandardException
     */
    public List<TypeResponsabiliteType.Parametres.TypeResponsabilite> selectTypeResponsabiliteByPredicat(final String predicat, final SelectTypeResponsabiliteRequestType.Pagination paginationIn) throws StandardException {
        SelectTypeResponsabiliteRequestType request = new SelectTypeResponsabiliteRequestType();
        SelectTypeResponsabiliteResponseType response = null;
        List<TypeResponsabiliteType.Parametres.TypeResponsabilite> result = null;
        request.setBranch(BranchEnum.GASEL.getCode());
        request.setInstance(InstanceEnum.PARAMETRE.getCode());
        request.setPredicate(predicat);
        if (parametresEBXInterface != null) {
            response = parametresEBXInterface.selectTypeResponsabiliteOperation(request, BusinessUtils.securityTokenMapper());
        }
        if (response != null && response.getData().getParametres().getTypeResponsabilite() != null && !response.getData().getParametres().getTypeResponsabilite().isEmpty()) {
            result = response.getData().getParametres().getTypeResponsabilite();
        }
        return result;
    }

    /**
     *
     * @return
     * @throws StandardException
     */
    public List<TypeMandatType.Parametres.TypeMandat> getAllTypeMandat() throws StandardException {
        SelectTypeMandatResponseType rep = findTypeMandatByPredicat("");
        List<TypeMandatType.Parametres.TypeMandat> typeMandatList = new ArrayList<TypeMandatType.Parametres.TypeMandat>();
        if (rep.getData().getParametres().getTypeMandat() != null && !rep.getData().getParametres().getTypeMandat().isEmpty()) {
            typeMandatList.addAll(rep.getData().getParametres().getTypeMandat());
        }
        return typeMandatList;
    }

    /**
     *
     * @param predicat
     * @return
     * @throws StandardException
     */
    public SelectTypeMandatResponseType findTypeMandatByPredicat(String predicat) throws StandardException {
        SelectTypeMandatRequestType typeMandatRequest = new SelectTypeMandatRequestType();
        typeMandatRequest.setBranch(BranchEnum.GASEL.getCode());
        typeMandatRequest.setInstance(InstanceEnum.PARAMETRE.getCode());
        typeMandatRequest.setPredicate(predicat);
        SelectTypeMandatResponseType typeMandatResponse = parametresEBXInterface.selectTypeMandatOperation(typeMandatRequest, BusinessUtils.securityTokenMapper());
        return typeMandatResponse;
    }

}
