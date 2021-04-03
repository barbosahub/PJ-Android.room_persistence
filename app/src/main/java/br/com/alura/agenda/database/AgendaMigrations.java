package br.com.alura.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class AgendaMigrations {
    static final Migration[] TODAS_MIGRATIONS = {new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //alterar tabela conforme campos adicionados
            //database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    }, new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //remover os parametros, setters e getters para rollback.

            //CRIAR NOVA TABELA COM INFORMACOES DESEJADAS
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefone` TEXT, " +
                    "`email` TEXT)");

            //COPIAR DADOS DA TABELA ANTIGA PARA NOVA
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email)" +
                    "SELECT id, nome, telefone, email FROM Aluno");

            //REMOVER TABELA ANTIGA
            database.execSQL("DROP TABLE Aluno");

            //RENOMEAR TABELA NOVA COM O NOME DA ANTIGA
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    }, new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN dt_cadastro INTEGER");
        }
    }};
}
