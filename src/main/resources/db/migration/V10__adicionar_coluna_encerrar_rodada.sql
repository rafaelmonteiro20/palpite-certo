ALTER TABLE rodada ADD is_encerrada char(1);
UPDATE rodada SET is_encerrada = 'F' WHERE id > 0;