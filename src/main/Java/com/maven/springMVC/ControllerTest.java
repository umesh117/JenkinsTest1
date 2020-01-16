package com.maven.springMVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;

@Controller
public class ControllerTest {


    @RequestMapping("/")
    public String CreateBeanProcess(Model theModel){
        theModel.addAttribute("userdata",new UserBean());
        return "index";
    }

    @RequestMapping("/signuppage")
    public String signuppage(Model theModel){
        theModel.addAttribute("userdata",new UserBean());
        return "index";
    }

    @RequestMapping("/loginpage")
    public String loginpage(){
        return "loginpage";
    }


    @RequestMapping("/processform")
    public String LoadData(@ModelAttribute("userdata")UserBean user1, @RequestParam("profile") CommonsMultipartFile file, HttpSession session){
        try {
            DatabaseHelper dbh=new DatabaseHelper();
            UserBean useremail=dbh.checkmail(user1.getEmail());
            UserBean userName=dbh.viewData(user1.getUsername());
            if(useremail==null && userName==null) {
                String pass=user1.getPassword();
                MessageDigest md=MessageDigest.getInstance("SHA-512");
                byte[] digestmsg=md.digest(pass.getBytes());
                user1.setPassword(Base64.getEncoder().encodeToString(digestmsg));

//For processing profile pic
                String path="/Images";
                ServletContext context=session.getServletContext();
                String newPath=context.getRealPath(path);
                String filename=file.getOriginalFilename();
                System.out.println(Paths.get(newPath));

                //for removing duplicate files
                int num=0;
                while(Files.exists(Paths.get(newPath+num+filename))){
                    ++num;
                }
                filename=num+filename;

                //setting filename
                user1.setFilename(filename);

                try{
                    byte barr[]=file.getBytes();
                    BufferedOutputStream bout=new BufferedOutputStream(
                            new FileOutputStream(newPath+"\\"+filename));
                    bout.write(barr);
                    bout.flush();
                    bout.close();

                }catch(Exception e){System.out.println(e);}
//---------

                dbh.insertData(user1);
            }else{
                if (useremail!=null) {
                    user1.setEmail("Email already registered.");
                }else if(userName!=null){
                    user1.setUsername("Username already exist.");
                }
                return "index";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "loginpage";
    }

    @RequestMapping("/processlogin")
    public String processLogin(@RequestParam("txtuser")String username, @RequestParam("txtpassword")String password,
                               Model model,HttpSession session) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, IOException {



        DatabaseHelper dbh=new DatabaseHelper();
        UserBean user=dbh.viewData(username);
        if(user==null){
            String errormessage="Username not registered.";
            model.addAttribute("message",errormessage);
            return "loginpage";
        }

        MessageDigest md=MessageDigest.getInstance("SHA-512");
        byte[] digestmsg=md.digest(password.getBytes());
        password=Base64.getEncoder().encodeToString(digestmsg);


        if(password.equals(user.getPassword())) {

            model.addAttribute("userdetails",user);
            model.addAttribute("profilepic",processImage(user.getFilename(),session));
            return "done";
        }else{
            String errormessage="Wrong Password.";
            model.addAttribute("message",errormessage);
            return "loginpage";
        }
    }

    public String processImage(String imagePath,HttpSession session) throws IOException {
        ServletContext context=session.getServletContext();
        String path="/Images";
        String realImgPath=context.getRealPath(path+"\\"+imagePath);

        byte[] imgbytes=Files.readAllBytes(Paths.get(realImgPath));
        String imgStr="data:image/png;base64,"+Base64.getEncoder().encodeToString(imgbytes);
        return imgStr;
    }

}
