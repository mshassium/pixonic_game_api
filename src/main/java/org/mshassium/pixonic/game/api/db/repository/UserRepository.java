package org.mshassium.pixonic.game.api.db.repository;

import org.mshassium.pixonic.game.api.db.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
