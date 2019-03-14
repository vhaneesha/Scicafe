package scicafe.model.dao;

import java.util.List;
import scicafe.model.Program;

public interface ProgramDao {
	
	List<Program>getAllPrograms();
    Program getProgram(Long id);
    void createProgram(Program p);
    Program editProgram(Program p);
    void deleteProgram(Program p);
}
