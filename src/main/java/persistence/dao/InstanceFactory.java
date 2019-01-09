package persistence.dao;

import persistence.dao.usuario.UsuarioDao;

public class InstanceFactory {

    private InstanceFactory(){}

    public static final String USUARIO_DAO= "USUARIODAO";
    private static final UsuarioDao usuarioDao = new UsuarioDao();

    public static ModelDao getInstance(String className) throws Exception{
        ModelDao returnValue = null;
        switch (className.toUpperCase()){
            case USUARIO_DAO:
                returnValue = usuarioDao;
        }
        return returnValue;
    }
}
