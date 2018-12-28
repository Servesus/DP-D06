
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CurriculaRepository;
import domain.Curricula;

@Component
@Transactional
public class StringToCurriculaConverter implements Converter<String, Curricula> {

	@Autowired
	CurriculaRepository	curriculaRepository;


	@Override
	public Curricula convert(final String text) {
		Curricula result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.curriculaRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
