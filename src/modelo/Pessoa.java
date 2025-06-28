package modelo;

public class Pessoa {

    private int idPessoa;
        private String nome;
        private String telefone;
        private String email;

        public Pessoa() {

        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getIdPessoa() {
            return idPessoa;
        }

        public void setIdPessoa(int idPessoa) {
            this.idPessoa = idPessoa;
        }

        public Pessoa(int idPessoa, String nome, String telefone, String email) {
            this.idPessoa = idPessoa;
            this.nome = nome;
            this.telefone = telefone;
            this.email = email;
        }

        @Override
        public String toString() {
            return          "id='" + idPessoa + '\'' +
                            ", nome='" + nome + '\'' +
                            ", telefone='" + telefone + '\'' +
                            ", email='" + email + '\'';
        }

}
