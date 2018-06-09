ALTER TABLE palpite ADD is_palpite_certo char(1);
UPDATE palpite SET is_palpite_certo = 'F' WHERE id > 0;