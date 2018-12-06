package service;

public class LogInService {
//		private String[] user= {"root"};
//		private String[] password= {"root"};
//		private boolean result;
	
		public LogInService() {
//			result=false;
		}
		
		public boolean authenticate(String user, String password) {
			if(user.equals("root") && password.equals("root")) {
				return true;
			}else {
				return false;
			}
		}
}
