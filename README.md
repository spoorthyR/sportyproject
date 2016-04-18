# sportyproject

Todo
-----
1.List every branch present in given ruby project git repository.
2.List every tag present in given ruby project git repository.
3.check each branch if it has .travis.yml and print without travis branches.
4.check each tag if it has .travis.yml and print without travis tags.

SPORTY PROJECT
--------------
My Aim is to list out and print all the branches and tags not having .travis.yml from Ruby project git repository.

ReadRepo.java -> ReadRepo.class
ReadRepoTest.java ->ReadRepoTest.class
run unit test
pom.xml

Step 1:
1.Listing all the branches
2.check the repository having travis file in their branches
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
		3.check the repository having travis file in their tags
			List<RepositoryContents> content = contService.getContents(repoID, null, repo.getName());
			
			if(!readRepo.hasTravisFile(content)){
				tagsWithoutTravisList.add(repo);
			}

			System.out.println(MessageFormat.format(PRINT_FORMAT, count++,
					repo.getName()));
			
		}
		readRepo.printTagsWithoutTravis(tagsWithoutTravisList);
			
	}
	4.check and print repository having .travis.yml file in their branches and tags.
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
	
		
