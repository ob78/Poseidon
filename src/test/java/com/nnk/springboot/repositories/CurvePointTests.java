package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.nnk.springboot.domain.Trade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/data-test.sql"})
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepositoryUnderTest;

	@Test
	public void curvePointTests() {
		//CurvePoint curvePointTest = new CurvePoint(10, 10d, 30d);

		CurvePoint curvePointTest = new CurvePoint();
		curvePointTest.setCurveId(10);
		curvePointTest.setAsOfDate(Timestamp.valueOf(LocalDateTime.now()));
		curvePointTest.setTerm(10d);
		curvePointTest.setValue(30d);
		curvePointTest.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));

		// Save
		curvePointTest = curvePointRepositoryUnderTest.save(curvePointTest);
		assertNotNull(curvePointTest.getId());
		assertTrue(curvePointTest.getCurveId() == 10);

		// Update
		curvePointTest.setCurveId(20);
		curvePointTest = curvePointRepositoryUnderTest.save(curvePointTest);
		assertTrue(curvePointTest.getCurveId() == 20);

		// Find by id
		Optional<CurvePoint> curvePointGet = curvePointRepositoryUnderTest.findById(curvePointTest.getId());
		assertTrue(curvePointGet.isPresent());
		assertEquals(curvePointTest.getCurveId(), curvePointGet.get().getCurveId());
		assertEquals(curvePointTest.getAsOfDate(), curvePointGet.get().getAsOfDate());
		assertEquals(curvePointTest.getTerm(), curvePointGet.get().getTerm());
		assertEquals(curvePointTest.getValue(), curvePointGet.get().getValue());
		assertEquals(curvePointTest.getCreationDate(), curvePointGet.get().getCreationDate());

		// Find all
		List<CurvePoint> listResult = curvePointRepositoryUnderTest.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = curvePointTest.getId();
		curvePointRepositoryUnderTest.delete(curvePointTest);
		Optional<CurvePoint> curvePointList = curvePointRepositoryUnderTest.findById(id);
		assertFalse(curvePointList.isPresent());
	}

}