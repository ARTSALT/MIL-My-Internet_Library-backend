-- 1. Tabela de Usuários
CREATE TABLE "users" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabela de Livros
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(150) NOT NULL,
    publish_year INT NOT NULL,s
    description TEXT,
    isbn VARCHAR(20) UNIQUE
);

-- 3. Tabela Intermediária (A relação com atributos)
CREATE TABLE user_books (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    rating INT CHECK (rating >= 1 AND rating <= 5), -- Nota estilo IMDB (1 a 5)
    status VARCHAR(20) NOT NULL, -- Ex: 'QUERO_LER', 'LENDO', 'LIDO'
    comment TEXT,
    
    -- Chaves Estrangeiras (Garantem a integridade referencial)
    CONSTRAINT fk_user_books_user FOREIGN KEY (user_id) REFERENCES "users"(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_books_book FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    
    -- Restrição Única: O mesmo usuário não pode adicionar o mesmo livro duas vezes
    CONSTRAINT uk_user_book_combination UNIQUE (user_id, book_id)
);