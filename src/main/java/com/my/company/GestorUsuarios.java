    package com.my.company;

    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    public class GestorUsuarios {
        //No necesito una lista porque un id solo identifica a un usuario
        //private Map<Integer, List<Usuario>> listaUsuarios;
        private Map<Integer, Usuario> usuarios;

        public GestorUsuarios(){
            this.usuarios = new HashMap<>();
        }

        public void guardarUsuario(Usuario u){
            if (u == null){
                throw new IllegalArgumentException("El usuario no puede estar vacio");
            }
            usuarios.put(u.getId(), u);
        }

        public Usuario buscarUsuario(int id){
            Usuario u = usuarios.get(id);
            if (u == null){
                throw new IllegalArgumentException("El usuario no existe");
            }
            return u;
        }

        public Map<Integer, Usuario> obtenerMapaUsuarios(){
            return new HashMap<>(usuarios);
        }

    }
