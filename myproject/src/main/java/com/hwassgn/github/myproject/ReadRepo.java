package com.hwassgn.github.myproject;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryBranch;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.RepositoryTag;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;

/**
 * Print a user's repositories
 */
public class ReadRepo {

	private static final String PRINT_FORMAT = "{0}) {1}";
	private static final String TRAVIS_YML = ".travis.yml";


	/**
	 * Prints a user's repositories
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		ReadRepo readRepo = new ReadRepo();
		final String user = "spoorthyR";
		int count = 1;
		RepositoryId repoID = RepositoryId.createFromUrl("https://github.com/ruby/ruby");
		RepositoryService service = new RepositoryService();
		ContentsService contService = new ContentsService();
 		Repository ruby = service.getRepository(repoID);
		System.out.println(MessageFormat.format(PRINT_FORMAT, count++,
				ruby.getName()));
		
		List<RepositoryBranch>  branchesWithoutTravisList = new ArrayList<RepositoryBranch>();
		
		for (RepositoryBranch  repo : service.getBranches(repoID)){
			List<RepositoryContents> content = contService.getContents(repoID, null, repo.getName());
			
			if(!readRepo.hasTravisFile(content)){
				branchesWithoutTravisList.add(repo);
			}
			System.out.println(MessageFormat.format(PRINT_FORMAT, count++,
					repo.getName()));
		}
		readRepo.printBranchsWithOutTravis(branchesWithoutTravisList);
		
		System.out.println("List of Tags :");
		List<RepositoryTag>  tagsWithoutTravisList = new ArrayList<RepositoryTag>();
		
		count = 1;
		for (RepositoryTag repo : service.getTags(repoID)){
			List<RepositoryContents> content = contService.getContents(repoID, null, repo.getName());
			
			if(!readRepo.hasTravisFile(content)){
				tagsWithoutTravisList.add(repo);
			}

			System.out.println(MessageFormat.format(PRINT_FORMAT, count++,
					repo.getName()));
			
		}
		
		readRepo.printTagsWithoutTravis(tagsWithoutTravisList);
			
	}
	
	
	public boolean hasTravisFile(List<RepositoryContents> contentList){
		boolean found = false;
		for(RepositoryContents contents:contentList){
			if(checkIfFileNameisTravisYml(contents)){
				return true;
			}
		}
		return found;
	}


	private boolean checkIfFileNameisTravisYml(RepositoryContents contents) {
		return TRAVIS_YML.equals(contents.getName());
		
	}
	
	private void printBranchsWithOutTravis(List<RepositoryBranch> branchesWithoutTravisList){
		System.out.println("List of Branches without file .travis.yml:");
		int count = 1;
		for (RepositoryBranch branch : branchesWithoutTravisList){
			System.out.println(MessageFormat.format(PRINT_FORMAT, count ++,
					branch.getName()));	
		}
	}
	
	private void printTagsWithoutTravis(List<RepositoryTag> tagsWithoutTravisList){
		System.out.println("List of Tags without file .travis.yml:");
		int count = 1;
		for (RepositoryTag tag : tagsWithoutTravisList){
			System.out.println(MessageFormat.format(PRINT_FORMAT, count ++,
					tag.getName()));	
		}
	}
}