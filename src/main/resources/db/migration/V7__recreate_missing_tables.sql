-- V7__recreate_all_tables.sql
-- =============================================
-- SEQUENCES (cria se não existirem)
-- =============================================
BEGIN
EXECUTE IMMEDIATE
    'CREATE SEQUENCE SQ_RESIDUO START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
        -- ORA-00955: name is already used by an existing object
        RAISE;
END IF;
END;

/
BEGIN
EXECUTE IMMEDIATE
    'CREATE SEQUENCE SQ_COLETA START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
        RAISE;
END IF;
END;

/
BEGIN
EXECUTE IMMEDIATE
    'CREATE SEQUENCE SQ_ALERTA START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
        RAISE;
END IF;
END;

/
BEGIN
EXECUTE IMMEDIATE
    'CREATE SEQUENCE SQ_USUARIO START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
        RAISE;
END IF;
END;

/
-- =============================================
-- TABELAS (cria se não existirem)
-- =============================================
-- Tabela: TB_RESIDUO
BEGIN
EXECUTE IMMEDIATE ' CREATE TABLE TB_RESIDUO (                             ID NUMBER(19) NOT NULL,                             TIPO VARCHAR2(60) NOT NULL,                             DESCRICAO VARCHAR2(400),                             PESO_MEDIO_KG NUMBER(10,3),                             CONSTRAINT PK_TB_RESIDUO PRIMARY KEY (ID) )';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
        RAISE;
END IF;
END;

/
BEGIN
EXECUTE IMMEDIATE
    'COMMENT ON TABLE TB_RESIDUO IS ''Tipos de resíduos (papel, plástico, metal etc.)'''
;
EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

/
BEGIN
EXECUTE IMMEDIATE
    'COMMENT ON COLUMN TB_RESIDUO.PESO_MEDIO_KG IS ''Peso médio por unidade (em kg), se aplicável.'''
;
EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

/
-- Tabela: TB_ALERTA
BEGIN
EXECUTE IMMEDIATE ' CREATE TABLE TB_ALERTA (                            ID NUMBER(19) NOT NULL,                            MENSAGEM VARCHAR2(400) NOT NULL,                            TIPO VARCHAR2(40) NOT NULL,                            ATIVO NUMBER(1) DEFAULT 1 NOT NULL,                            CONSTRAINT PK_TB_ALERTA PRIMARY KEY (ID) )';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
        RAISE;
END IF;
END;

/
BEGIN
EXECUTE IMMEDIATE
    'COMMENT ON TABLE TB_ALERTA IS ''Alertas automáticos e manuais relacionados a resíduos/coletas.'''
;
EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

/
BEGIN
EXECUTE IMMEDIATE
    'COMMENT ON COLUMN TB_ALERTA.ATIVO IS ''Flag lógico: 1=ativo, 0=inativo.'''
;
EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

/
-- Tabela: TB_COLETA
BEGIN
EXECUTE IMMEDIATE ' CREATE TABLE TB_COLETA (                            ID NUMBER(19) NOT NULL,                            DATA_COLETA TIMESTAMP(6) NOT NULL,                            LOCAL_COLETA VARCHAR2(200) NOT NULL,                            RESPONSAVEL VARCHAR2(120) NOT NULL,                            RESIDUO_ID NUMBER(19) NOT NULL,                            QUANTIDADE_KG NUMBER(12,3) NOT NULL,                            CONSTRAINT PK_TB_COLETA PRIMARY KEY (ID) )';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
        RAISE;
END IF;
END;

/
-- Foreign Key da TB_COLETA
BEGIN
EXECUTE IMMEDIATE
    ' ALTER TABLE TB_COLETA     ADD CONSTRAINT FK_COLETA_RESIDUO         FOREIGN KEY (RESIDUO_ID) REFERENCES TB_RESIDUO (ID)';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -2275 THEN
        -- ORA-02275: such a referential constraint already exists
        RAISE;
END IF;
END;

/
BEGIN
EXECUTE IMMEDIATE
    'COMMENT ON TABLE TB_COLETA IS ''Registros de coletas de material reciclável.'''
;
EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

/
BEGIN
EXECUTE IMMEDIATE
    'COMMENT ON COLUMN TB_COLETA.QUANTIDADE_KG IS ''Quantidade coletada em quilogramas.'''
;
EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

/
-- Tabela: TB_USUARIO
BEGIN
EXECUTE IMMEDIATE ' CREATE TABLE TB_USUARIO (                             ID NUMBER(19) NOT NULL,                             USERNAME VARCHAR2(80) NOT NULL UNIQUE,                             PASSWORD VARCHAR2(200) NOT NULL,                             ROLE VARCHAR2(20) DEFAULT ''USER'' NOT NULL,                             CONSTRAINT PK_TB_USUARIO PRIMARY KEY (ID) )';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
        RAISE;
END IF;
END;

/
-- =============================================
-- ÍNDICES (cria se não existirem)
-- =============================================
BEGIN
EXECUTE IMMEDIATE 'CREATE INDEX IX_COLETA_RESIDUO ON TB_COLETA (RESIDUO_ID)'
;
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
        RAISE;
END IF;
END;

/
BEGIN
EXECUTE IMMEDIATE 'CREATE INDEX IX_COLETA_DATA ON TB_COLETA (DATA_COLETA)';
EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
        RAISE;
END IF;
END;

/