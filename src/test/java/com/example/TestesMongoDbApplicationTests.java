package com.example;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Coleta;
import com.example.repository.ColetaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestesMongoDbApplicationTests {

	@Autowired
	private ColetaRepository coletaRepository;
	
	@Before
	public void setup()
	{
		List<Coleta> list = new ArrayList<Coleta>();
		
		list.add(new Coleta("Ita", "São Paulo", LocalDate.of(2017, 05, 01)));	
		list.add(new Coleta("Ita", "São Paulo", LocalDate.of(2017, 05, 02)));	
		list.add(new Coleta("Ita", "São Paulo", LocalDate.of(2017, 05, 03)));	
		list.add(new Coleta("Ita", "Rio de Janeiro", LocalDate.of(2017, 05, 01)));	
		list.add(new Coleta("Ita", "Rio de Janeiro", LocalDate.of(2017, 05, 02)));	
		list.add(new Coleta("Ita", "Curitiba", LocalDate.of(2017, 05, 01)));	
		list.add(new Coleta("Carol", "Curitiba", LocalDate.of(2017, 05, 01)));	
		list.add(new Coleta("Carol", "São Paulo", LocalDate.of(2017, 05, 01)));	
		list.add(new Coleta("Trend", "Curitiba", LocalDate.of(2017, 05, 01)));	
		list.add(new Coleta("Trend", "São Paulo", LocalDate.of(2017, 05, 01)));	
		list.add(new Coleta("TrendFlash", "Curitiba", LocalDate.of(2017, 05, 01)));	
		list.add(new Coleta("TrendFlash", "São Paulo", LocalDate.of(2017, 05, 01)));	
		
		coletaRepository.save(list);
	
	}
	
	@After
	public void tearDown()
	{
		coletaRepository.deleteAll();
	}
	
	@Test
	public void findAllColetas()
	{
		assertEquals(12, coletaRepository.findAll().size());
		assertEquals(12, coletaRepository.count());
	}
	

	@Test
	public void findByCheckIn() {
		assertEquals(9, coletaRepository.findByCheckin(LocalDate.of(2017, 05, 01)).size());
		assertEquals(2, coletaRepository.findByCheckin(LocalDate.of(2017, 05, 02)).size());
		assertEquals(1, coletaRepository.findByCheckin(LocalDate.of(2017, 05, 03)).size());
		assertEquals(0, coletaRepository.findByCheckin(LocalDate.of(1900, 05, 03)).size());
	}
	
	@Test
	public void findByCidade() {
		assertEquals(6, coletaRepository.findByCidade("São Paulo").size());
		assertEquals(2, coletaRepository.findByCidade("Curitiba").size());
		assertEquals(2, coletaRepository.findByCidade("Rio de Janeiro").size());
		assertEquals(0, coletaRepository.findByCidade("Recife").size());
	}
	
	@Test
	public void findByFornecedor() {
		assertEquals(6, coletaRepository.findByFornecedor("Ita").size());
		assertEquals(2, coletaRepository.findByFornecedor("Carol").size());
		assertEquals(0, coletaRepository.findByFornecedor("Joca").size());	
		
	}
	
	@Test
	public void findByExample()
	{
		Coleta exemplo = new Coleta();
		exemplo.setFornecedor("Ita");
    
		Example<Coleta> example = Example.of(exemplo);
		assertEquals(6L, coletaRepository.findAll(example).size());
	}
	
	@Test
	public void deleteOne()
	{
		Coleta c = coletaRepository.findAll().get(0);
		coletaRepository.delete(c);
		assertEquals(11, coletaRepository.count());
	}
	
	@Test
	public void deleteBy_CheckIn_Fornecedor_Cidade() {
		assertEquals(12, coletaRepository.count());
		coletaRepository.deleteBy(LocalDate.of(2017, 05, 03), "Ita", "São Paulo");
		assertEquals(11, coletaRepository.count());
	}
	@Test
	public void countNotTrend() {
		assertEquals(8L, coletaRepository.countNotTrendNorTrendFlash().longValue());
	}
	@Test
	public void countBy_CheckIn() {
		
		assertEquals(5L, coletaRepository.countNotTrendNorFlashByDate(LocalDate.of(2017, 05, 01)).longValue());
	}
	
}