package dev.java10x.CadastroDeNinja.Missoes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MissoesRepository extends JpaRepository<MissoesModel, Long> { //O 'JPA' é apenas uma abstração para as query para o nosso BD.
}
