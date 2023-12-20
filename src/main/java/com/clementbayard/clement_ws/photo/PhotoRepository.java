package com.clementbayard.clement_ws.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {

    List<Photo> getPhotosByPhotographeIdAndType(long id, String type);

    List<Photo> getPhotosByPhotographeId(long id);


}
