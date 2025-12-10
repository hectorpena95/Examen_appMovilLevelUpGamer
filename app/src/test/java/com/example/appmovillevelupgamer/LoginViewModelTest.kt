import com.example.appmovillevelupgamer.presentacion.login.LoginViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginViewModelTest {

    @Test
    fun `login correcto devuelve nombre`() {
        val vm = LoginViewModel()

        vm.correo = "hector"
        vm.contrasena = "hector123"

        val resultado = vm.validarLogin()

        assertEquals("Héctor Peña", resultado)
    }

    @Test
    fun `login incorrecto devuelve null`() {
        val vm = LoginViewModel()

        vm.correo = "hector"
        vm.contrasena = "malaClave"

        val resultado = vm.validarLogin()

        assertEquals(null, resultado)
        assertEquals("Correo o contraseña incorrectos", vm.errorMensaje)
    }
}
