import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public user = {
    username : '',
    password : '',
    nome : '',
    apellido : '',
    email : '',
    telefone : ''
  }

  constructor(private userService:UserService, private snack:MatSnackBar) { }

  ngOnInit(): void {
  }

  formSubmit(){
    if(this.user.username == '' || this.user.username == null ){
      this.snack.open('Nome do usuario é necessario !!','continuar',{
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'right'
      })
      return
    }
    this.userService.registrarUsuario(this.user).subscribe(
      (data) => {
        console.log(data)
        Swal.fire('Usuario Registrado com Sucesso','Usuario Registrado com Sucesso','success')
      },(error) => {
        console.log(error)
        this.snack.open('Ocorreu um erro no sistema !!','continuar',{
          duration: 3000,
          
        })
      }
    )
  }

}
