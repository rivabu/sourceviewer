package com.rients.org.sourceviewer;

import static org.springframework.http.HttpStatus.OK;

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

import com.rients.org.sourceviewer.domain.ReturnId;
import com.rients.org.sourceviewer.domain.Tree;
import com.rients.org.sourceviewer.domain.TreeElement;
import com.rients.org.sourceviewer.domain.Type;
import com.rients.org.sourceviewer.service.TreeBo;
import com.rients.org.sourceviewer.service.TreeService;

@RestController
public class TreeRestController {
	@Autowired
	private TreeService treeService;

	
    
	
	@RequestMapping(value = "/tree/{id}", method = RequestMethod.GET)
	public ResponseEntity<Tree> getTreeById(@PathVariable("id") int id) {
		TreeBo treeBo = treeService.getTreeById(id);
		
		Tree tree = convertToTree(treeBo);
		ResponseEntity<Tree> response = new ResponseEntity<Tree>(tree, OK);
		return response;
	}

	private Tree convertToTree(TreeBo treeBo) {
		Tree tree = new Tree();
		tree.setId(treeBo.getId());
		List<TreeElement> list = tree.getElements();
		for (com.rients.org.sourceviewer.service.TreeElement elem: treeBo.getElements()) {
			TreeElement e2 = new TreeElement();
			e2.setId(elem.getId());
			e2.setType(Type.valueOf(elem.getType()));
			e2.setName(elem.getName());
			e2.setExtension(elem.getExtension());
			e2.setFileId(elem.getFileId());
			list.add(e2);
		}
		return tree;
	}
	
	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	public ResponseEntity<ReturnId> addProject( @Valid @RequestBody Tree tree) throws MethodArgumentNotValidException {
		TreeBo treeBo = convertToTreeBo(tree);
		TreeBo oldTreeBo = treeService.getTreeById(treeBo.getId());
		if (oldTreeBo != null) {
			treeService.removeTree(treeBo.getId());
		}
		treeService.addTree(treeBo);
		return new ResponseEntity<>(new ReturnId(tree.getId()), OK);

	}
	
	@RequestMapping(value = "/tree", method = RequestMethod.PUT)
	public ResponseEntity<ReturnId> updateProject( @Valid @RequestBody Tree tree) throws MethodArgumentNotValidException {
		if (treeService.getTreeById(tree.getId()) != null) {
			TreeBo treeBo = convertToTreeBo(tree);
			treeService.updateTree(treeBo);
		} else {
			// tree not found!
		}
		return new ResponseEntity<>(new ReturnId(tree.getId()), OK);

	}

	private TreeBo convertToTreeBo(Tree tree) {
		TreeBo treeBo = new TreeBo();
		treeBo.setId(tree.getId());
		List<com.rients.org.sourceviewer.service.TreeElement> list = treeBo.getElements();
		for (TreeElement elem: tree.getElements()) {
			com.rients.org.sourceviewer.service.TreeElement e2 = new com.rients.org.sourceviewer.service.TreeElement();
			e2.setId(elem.getId());
			e2.setType(elem.getType().name());
			e2.setName(elem.getName());
			e2.setExtension(elem.getExtension());
			e2.setFileId(elem.getFileId());
			list.add(e2);
		}
		return treeBo;
	}
	
	@RequestMapping(value = "/tree/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ReturnId> deleteProject( @PathVariable("id") int id) throws MethodArgumentNotValidException {
		if (treeService.getTreeById(id) != null) {
			treeService.removeTree(id);;
		} else {
			// tree not found!
		}
		return new ResponseEntity<>(new ReturnId(id), OK);

	}
 
}

