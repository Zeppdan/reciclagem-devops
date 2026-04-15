-- =============================================
-- Remove versões antigas se existirem
-- =============================================
BEGIN
EXECUTE IMMEDIATE 'DROP TABLE TBL_USUARIOS CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
END IF;
END;

/
BEGIN
EXECUTE IMMEDIATE 'DROP SEQUENCE SEQ_USUARIOS';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -2289 THEN -- ORA-02289: sequence does not exist
        RAISE;
END IF;
END;

/
-- =============================================
-- Criação da sequência e tabela
-- =============================================
CREATE SEQUENCE seq_usuarios
    START WITH 1
    INCREMENT BY 1
    NOCACHE
  NOCYCLE;

/
CREATE TABLE tbl_usuarios
(
    usuario_id INTEGER DEFAULT seq_usuarios.NEXTVAL NOT NULL,
    nome       VARCHAR2(100) NOT NULL,
    email      VARCHAR2(100) NOT NULL,
    senha      VARCHAR2(20) NOT NULL
);

/
-- =============================================
-- Alterações adicionais (constraints e ajustes)
-- =============================================
ALTER TABLE tbl_usuarios
    ADD ROLE VARCHAR2(50) DEFAULT 'USER' NOT NULL;

/
ALTER TABLE tbl_usuarios
    ADD CONSTRAINT email_unico UNIQUE (email);

/
ALTER TABLE tbl_usuarios
    MODIFY senha VARCHAR2(100);

/
-- =============================================
-- Comentários (opcional, boa prática)
-- =============================================
COMMENT ON TABLE tbl_usuarios IS 'Tabela de usuários do sistema';

COMMENT ON COLUMN tbl_usuarios.usuario_id IS 'Identificador único do usuário';

COMMENT ON COLUMN tbl_usuarios.nome IS 'Nome completo do usuário';

COMMENT ON COLUMN tbl_usuarios.email IS 'E-mail único usado para login';

COMMENT ON COLUMN tbl_usuarios.senha IS 'Senha criptografada do usuário';

COMMENT ON COLUMN tbl_usuarios.ROLE IS 'Papel do usuário (USER, ADMIN, etc)';

/