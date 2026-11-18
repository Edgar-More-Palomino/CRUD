package crud_mysql.CRUD.service;

import crud_mysql.CRUD.entity.UserEntity;
import crud_mysql.CRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ArrayList<UserEntity> getusers() {
        return (ArrayList<UserEntity>) userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> getById(Long id) {
        return userRepository.findById(id);
    }

//    public UserEntity updateById(UserEntity userEntity, Long id) {
//        UserEntity userEntity1 = userRepository.findById(id).get();
//
//        userEntity1.setFirstName(userEntity.getFirstName());
//        userEntity1.setLastName(userEntity.getLastName());
//        userEntity1.setEmail(userEntity.getEmail());
//
//        return userEntity1;
//
//    }
public UserEntity updateById(UserEntity request, Long id) {
    // 1. Buscamos si el usuario existe
    UserEntity user = userRepository.findById(id).orElseThrow(() ->
            new RuntimeException("Usuario no encontrado con id: " + id));

    // 2. Actualizamos los campos necesarios
    user.setFirstName(request.getFirstName()); // (Usa los campos reales de tu entidad)
    user.setLastName(request.getLastName());
    user.setEmail(request.getFirstName());
    // ... otros campos ...

    // 3. Guardamos (Spring Data JPA detecta que el ID ya existe y hace un UPDATE)
    return userRepository.save(user);
}

    //    public Boolean deleteUser(Long id) {
//        try {
//            userRepository.deleteById(id);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
    public boolean deleteUser(Long id) {
        // Verificamos si el usuario realmente existe en la base de datos
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true; // Se eliminó con éxito
        } else {
            return false; // El ID no existe
        }
    }
}
