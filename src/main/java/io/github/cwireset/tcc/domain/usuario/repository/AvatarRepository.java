package io.github.cwireset.tcc.domain.usuario.repository;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "some-random-api", url = "https://some-random-api.ml")
@Repository
public interface AvatarRepository {

    @RequestMapping(method = RequestMethod.GET, value = "img/dog")
    Object getAvatar();
}
