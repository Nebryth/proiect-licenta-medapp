package ps_project.diagnose;

import org.springframework.data.repository.CrudRepository;

/**
 * Contains all the methods definitions for the existing 'diagnose' operations
 */
public interface DiagnoseRepository extends CrudRepository<DiagnoseModel, Integer> {

        DiagnoseModel findDiagnoseById(Integer id);
}
