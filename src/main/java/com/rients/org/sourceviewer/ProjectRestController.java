package com.rients.org.sourceviewer;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rients.org.sourceviewer.domain.Project;
import com.rients.org.sourceviewer.domain.ProjectData;
import com.rients.org.sourceviewer.domain.ReturnId;
import com.rients.org.sourceviewer.service.ProjectBo;
import com.rients.org.sourceviewer.service.ProjectService;

@RestController
public class ProjectRestController {
	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/project/list", method = RequestMethod.GET)
	public ResponseEntity<ProjectData> listProjects() {
		List<ProjectBo> projectBos = this.projectService.listProjects();
		List<Project> projecten = new ArrayList<Project>();
		for (ProjectBo projectBo : projectBos) {
			Project project = new Project();
			project.setId(projectBo.getId());
			project.setDescription(projectBo.getDescription());
			project.setName(projectBo.getName());
			projecten.add(project);
		}
		ProjectData projectData = new ProjectData();
		projectData.setProjecten(projecten);
		ResponseEntity<ProjectData> response = new ResponseEntity<ProjectData>(projectData, OK);
		return response;
	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {
		ProjectBo projectBo = null;
		ResponseEntity<Project> response = null;
		try {
			projectBo = this.projectService.getProjectById(id);

			Project project = new Project();
			project.setId(projectBo.getId());
			project.setDescription(projectBo.getDescription());
			project.setName(projectBo.getName());
			response = new ResponseEntity<Project>(project, OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(NOT_FOUND);
		}
		return response;
	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeProject(@PathVariable("id") int id) {
		this.projectService.removeProject(id);
		return new ResponseEntity<>(NO_CONTENT);
	}

	@RequestMapping(value = "/project", method = RequestMethod.POST)
	public ResponseEntity<ReturnId> addProject(@Valid @RequestBody ProjectBo p) throws MethodArgumentNotValidException {
		// if (bindingResult.hasErrors()) {
		// // This exception will be picked up by
		// CustomResponseEntityExceptionHandler.handleMethodArgumentNotValid
		// throw new MethodArgumentNotValidException(null, bindingResult);
		// }
		int id = 0;
		if (p.getId() == -1) {
			// new person, add it
			ProjectBo projectBo = this.projectService.getProjectByName(p.getName());
			if (projectBo != null) {
				this.projectService.removeProject(projectBo.getId());
			}
			id = this.projectService.addProject(p);
		} else {
			// throw exception
		}
		return new ResponseEntity<>(new ReturnId(id), OK);

	}

	@RequestMapping(value = "/project", method = RequestMethod.PUT)
	public ResponseEntity<ReturnId> updateProject(@Valid @RequestBody ProjectBo p) {
		this.projectService.updateProject(p);
		return new ResponseEntity<>(new ReturnId(p.getId()), OK);
	}

}
