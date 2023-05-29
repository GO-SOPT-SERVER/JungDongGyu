package sopt.org.fouthSeminar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.org.fouthSeminar.domian.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
