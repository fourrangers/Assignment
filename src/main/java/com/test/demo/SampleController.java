package com.test.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@EnableAutoConfiguration
@RequestMapping("/assignment/api/v1")
public class SampleController {

	@GetMapping("/display")
	public String home() {
		return "Hello World";
	}

	@ApiOperation(value = "returns nth Fibonacci number", response = Long.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved nth Fibonacci"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fibonacci/{number}")
	public Long getNthFibonnaci(@PathVariable(value = "number") Long number) {
		long a = 0;
		long b = 1;

		for (long i = 1; i <= number; i++) {
			long temp = a;
			a = a + b;
			b = temp;
		}
		return a;
	}

	@ApiOperation(value = "reverses letters of each word in a sentence", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully reversed each word"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/ReverseWords/{sentence}")
	public String reverseWords(@PathVariable(value = "sentence") String sentence) {
		String words[] = sentence.split(" ");
		String reverseWord = "";
		for (String w : words) {
			StringBuilder sb = new StringBuilder(w);
			sb.reverse();
			reverseWord += sb.toString() + " ";
		}
		return reverseWord.trim();
	}

	@ApiOperation(value = "Returns Type of Triangle", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully found the type of triangle"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/TriangleType/{lengthA}/{lengthB}/{lengthC}")
	public String triangleType(@PathVariable(value = "lengthA") int lengthA,
			@PathVariable(value = "lengthB") int lengthB, @PathVariable(value = "lengthC") int lengthC) {
		if (lengthA == lengthB && lengthB == lengthC)
			return "Equilateral";

		else if ((lengthA == lengthB && lengthB != lengthC) || (lengthA != lengthB && lengthC == lengthA)
				|| (lengthC == lengthB && lengthC != lengthA))
			return "Isosceles";

		else if (lengthA != lengthB && lengthB != lengthC && lengthC != lengthA)
			return "Scalene";

		return "Not a triangle";
	}

	@ApiOperation(value = "Combines multiple arrays to one array", response = Numbers.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully combined, sorted to one Array"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/makeOneArray")
	@RequestMapping(value = "makeOneArray", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Numbers makeOneArray(@RequestBody Numbers arrays) {
		List<Numbers> result = new ArrayList<>();
		Stream.of(arrays).flatMap(Stream::of).map(x -> (Numbers) x).forEach(result::add);
		return result.get(0);
	}

}
