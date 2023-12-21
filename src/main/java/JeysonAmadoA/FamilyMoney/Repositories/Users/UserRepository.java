package JeysonAmadoA.FamilyMoney.Repositories.Users;

import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Repositories.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

    List<UserEntity> findByEmailLike(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.name LIKE %:searchTerm% OR u.lastName LIKE %:searchTerm%")
    List<UserEntity> findByNameLikeOrLastNameLike(@Param("searchTerm") String searchTerm);

    Optional<UserEntity> findByEmail(String email);
}
