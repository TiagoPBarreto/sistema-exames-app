import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData = {
    "username":"",
    "password":""
  }
  router: any;
  constructor(private snack:MatSnackBar,private loginService:LoginService) { }

  ngOnInit(): void {
  }

  formSubmit(){
    if(this.loginData.username.trim() == '' || this.loginData.username.trim() == null){
      this.snack.open('Nome do usuario é necessario!','Continuar',{
        duration: 3000
      })
      return
    }
    if(this.loginData.password.trim() == '' || this.loginData.password.trim() == null){
      this.snack.open('Senha do usuario é necessario!','Continuar',{
        duration: 3000
      })
      return
    }

    this.loginService.generateToken(this.loginData).subscribe(
      (data:any) => {
        console.log(data)
        this.loginService.loginUser(data.token)
        this.loginService.getCurrentUser().subscribe((user:any)=>{
          this.loginService.setUser(user)
          console.log(user)
          if(this.loginService.getUserRole( ) == "ADMIN"){
            //window.location.href = '/admin'
            this.router.navigate(['admin'])
            this.loginService.loginStatusSubject.next(true)
          }
          else if(this.loginService.getUserRole( ) == "NORMAL"){
            //window.location.href = '/user-dashboard'
            this.router.navigate(['user-dashboard'])
            this.loginService.loginStatusSubject.next(true)

          }
          else{
            this.loginService.logout()
          }
        })
        
      },(error)=>{
        console.log(error)
        this.snack.open('Dados Invalidos, tente novamente!!','Continuar',{
          duration:3000
        })
      }
    )
  }
}
