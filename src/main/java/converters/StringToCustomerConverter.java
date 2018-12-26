
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomerRepository;
import domain.Customer;

@Component
@Transactional
public class StringToCustomerConverter implements Converter<String, Customer> {

	@Autowired
	CustomerRepository	customerRepository;


	@Override
	public Customer convert(final String text) {
		Customer result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.customerRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
